package com.backend.fitters.user;

import java.security.Key;
import java.util.List;
import java.util.Optional;
import com.backend.fitters.advice.NotFoundException;
import com.backend.fitters.auth.dto.UserDto;
import com.backend.fitters.auth.request.PasswordResetRequest;
import com.backend.fitters.config.JwtService;
import com.backend.fitters.advice.*;
import com.backend.fitters.user.dto.GetFriendsDto;
import com.backend.fitters.user.dto.GetUsersDto;
import com.backend.fitters.user.request.UpdateUserRequest;
import com.backend.fitters.util.MyUtils;
import com.backend.fitters.user.dto.SearchDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class UserService {

    @Value("${secretkey}")
    private String secretKey;

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
            JwtService jwtService,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public SearchDto searchUsers(String term, int page) {
        int currentPage = MyUtils.paginate(page, "next");
        Pageable paging = PageRequest.of(currentPage, 3, Sort.by("id"));
        Page<GetUsersDto> result = this.userRepository.searchUsers(term.toLowerCase(), paging);

        return new SearchDto(result.getContent(), result.getTotalPages(), currentPage);
    }

    public User getUserById(Long id) {
        return this.userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User getUserByAuth() {
        String currentUserName = "";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            currentUserName = auth.getName();
        }
        User user = this.userRepository.findByEmail(currentUserName)
                .orElseThrow(() -> new NotFoundException("User not found."));
        return user;

    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractUserIdFromToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public UserDto getUserByToken(String token) {
        Claims claims = extractUserIdFromToken(token);

        User user = getUserByEmail(claims.getSubject());
        UserDto userDto = new UserDto(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.getAbbreviation(),
                user.getProfile().getId(),
                true,
                user.getProfile().getAvatarUrl());
        return userDto;

    }

    public void resetUserPassword(PasswordResetRequest request) {

        User user = this.userRepository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException("User not found."));

        if (!this.jwtService.isTokenValid(request.getToken(), user)) {
            throw new ForbiddenException("Link has expired.");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match.");
        }
        user.setPassword(this.passwordEncoder.encode(request.getNewPassword()));
        this.userRepository.save(user);
    }

    public boolean checkOwnerShip(Long userId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User user = this.userRepository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return user.getId() == userId;
    }

    public void updateUser(Long userId, UpdateUserRequest request) {
        if (!checkOwnerShip(userId)) {
            throw new ForbiddenException("Cannot update another user.");
        }

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (request.getFirstName() != null) {
            user.setFirstName(MyUtils.capitalize(request.getFirstName()));
        }

        if (request.getLastName() != null) {
            user.setLastName(MyUtils.capitalize(request.getLastName()));
        }

        this.userRepository.save(user);

    }

    public List<GetFriendsDto> getFriends(List<Long> friendsIds) {
        return this.userRepository.getFriends(friendsIds);
    }
}
