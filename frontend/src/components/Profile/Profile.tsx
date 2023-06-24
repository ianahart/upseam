import { Box, Text, Flex, Image, Link } from '@chakra-ui/react';
import FormLayout from '../Menu/EditProfile/FormLayout';
import Header from '../Menu/Header';
import EditProfileLink from '../Menu/MyProfile/EditProfileLink';
import InitialIcon from '../Shared/InitialIcon';
import FormHeader from '../Menu/EditProfile/FormHeader';
import { IProfilePage, IUserContext } from '../../interfaces';
import { useContext } from 'react';
import { UserContext } from '../../context/user';
import { AiFillStar } from 'react-icons/ai';

interface IProfileProps {
  profile: IProfilePage;
  rating: number;
  isSeamster: boolean;
}

const Profile = ({ profile, rating, isSeamster }: IProfileProps) => {
  const { user } = useContext(UserContext) as IUserContext;
  return (
    <Box>
      <Header heading="Profile" />
      <FormLayout>
        <>
          {user.id === profile.profileUserId && <EditProfileLink />}

          <Flex alignItems="center">
            {profile.avatarUrl ? (
              <Image
                borderRadius="50%"
                src={profile.avatarUrl}
                alt={`avatar of ${profile.firstName}`}
              />
            ) : (
              <InitialIcon />
            )}
            <Box ml="1.5rem">
              <Text fontWeight="bold" color="black.primary" fontSize="1.1rem">
                {profile.firstName} {profile.lastName}
              </Text>
              {profile.state && profile.country && (
                <Text color="text.primary">
                  {profile.state}, {profile.country}
                </Text>
              )}
              <Flex>
                {isSeamster &&
                  [...Array(rating)].map((_, index) => {
                    return (
                      <Box key={index}>
                        <AiFillStar color={index <= rating ? 'orange' : 'black'} />
                      </Box>
                    );
                  })}
              </Flex>
            </Box>
          </Flex>
        </>
      </FormLayout>
      <FormLayout>
        <>
          <FormHeader heading="Personal Information" />
          <Flex
            direction={['column', 'row', 'row']}
            justify={['unset', 'space-between', 'space-between']}
          >
            <Flex
              direction={['column', 'row', 'row']}
              width={['95%', '95%', '400px']}
              justifyContent="space-between"
            >
              <Box>
                <Text color="text.primary">First Name</Text>
                <Text color="black.primary">{profile.firstName}</Text>
              </Box>
              <Box>
                <Text color="text.primary">Last Name</Text>
                <Text color="black.primary">{profile.lastName}</Text>
              </Box>
            </Flex>
            {user.id === profile.profileUserId && <EditProfileLink />}
          </Flex>
        </>
      </FormLayout>
      <FormLayout>
        <>
          <FormHeader heading="Address" />
          <Flex
            direction={['column', 'row', 'row']}
            justify={['unset', 'space-between', 'space-between']}
          >
            <Flex
              direction={['column', 'row', 'row']}
              width={['95%', '400px', '400px']}
              justifyContent="space-between"
            >
              <Box>
                <Box my="1rem">
                  <Text color="text.primary">Country</Text>
                  <Text color="black.primary">{profile.country}</Text>
                </Box>
                <Box my="1rem">
                  <Text color="text.primary">Address</Text>
                  <Text color="black.primary">{profile.address}</Text>
                </Box>
              </Box>
              <Box>
                <Box my="1rem">
                  <Text color="text.primary">State</Text>
                  <Text color="black.primary">{profile.state}</Text>
                </Box>
                <Box my="1rem">
                  <Text color="text.primary">Zip Code</Text>
                  <Text color="black.primary">{profile.zipCode}</Text>
                </Box>
              </Box>
            </Flex>
            {user.id === profile.profileUserId && <EditProfileLink />}
          </Flex>
        </>
      </FormLayout>
      {profile.role === 'SEAMSTER' && (
        <FormLayout>
          <>
            <FormHeader heading="About" />
            {user.id === profile.profileUserId && <EditProfileLink />}

            <Box>
              <Box my="1rem">
                <Text color="text.primary">Personal Site</Text>
                <Box color="blue.500">
                  <Link href={profile.site}>{profile.site}</Link>
                </Box>
              </Box>
              <Box my="1rem">
                <Text color="text.primary">Bio</Text>
                <Text color="black.primary">{profile.bio}</Text>
              </Box>
            </Box>
            <Box my="1rem">
              <FormHeader heading="Specialities" />
              {profile.specialities !== null && profile.specialities.length > 0 && (
                <Flex wrap="wrap" justifyContent="space-around">
                  {profile.specialities.map(({ id, text }) => {
                    return (
                      <Box key={id} borderRadius="8px" p="0.5rem" bg="gray.100">
                        <Text color="black.primary">{text}</Text>
                      </Box>
                    );
                  })}
                </Flex>
              )}
            </Box>
            <Flex justify="flex-start" direction="column" align="flex-start" my="1rem">
              <FormHeader heading="Typical price range" />
              <Text
                bg="rgba(16, 178, 16, 0.5)"
                p="0.5rem"
                borderRadius="8px"
                color="black.primary"
              >
                {profile.pricing}
              </Text>
            </Flex>
          </>
        </FormLayout>
      )}
    </Box>
  );
};
export default Profile;
