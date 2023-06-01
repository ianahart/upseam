package com.backend.fitters.bid.dto;

import com.backend.fitters.cloth.Cloth;
import com.backend.fitters.user.User;

public class RelatedEntityDto {
    private User user;
    private Cloth cloth;

    public RelatedEntityDto() {

    }

    public RelatedEntityDto(User user, Cloth cloth) {
        this.user = user;
        this.cloth = cloth;
    }

    public User getUser() {
        return user;
    }

    public Cloth getCloth() {
        return cloth;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCloth(Cloth cloth) {
        this.cloth = cloth;
    }
}
