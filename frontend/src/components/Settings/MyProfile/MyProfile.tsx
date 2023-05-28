import { Box, Image, Flex, Text, Link } from '@chakra-ui/react';
import { useContext, useEffect, useState } from 'react';
import { Client } from '../../../util/client';
import Header from '../Header';
import { UserContext } from '../../../context/user';
import { IProfilePage, ISpeciality, IUserContext } from '../../../interfaces';
import { profilePageState } from '../../../state/initialState';
import FormLayout from '../EditProfile/FormLayout';
import EditProfileLink from './EditProfileLink';
import FormHeader from '../EditProfile/FormHeader';
import InitialIcon from '../../Shared/InitialIcon';

const MyProfile = () => {
  const { user: contextUser } = useContext(UserContext) as IUserContext;
  const [profile, setProfile] = useState<IProfilePage>(profilePageState);

  useEffect(() => {
    if (contextUser.profileId !== 0) {
      fetchProfile();
    }
  }, [contextUser.profileId]);

  const fetchProfile = () => {
    Client.getProfile(contextUser.profileId)
      .then((res) => {
        const { data } = res.data;
        const { user, profile } = data;

        setProfile({
          ...user,
          ...profile,
          specialities: JSON.parse(profile.specialities),
        });
      })
      .catch((err) => {
        throw new Error(err.response.data.message);
      });
  };

  return (
    <Box>
      <Header heading="My Profile" />
      <FormLayout>
        <>
          <EditProfileLink />
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
            <EditProfileLink />
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
            <EditProfileLink />
          </Flex>
        </>
      </FormLayout>
      {contextUser.role === 'SEAMSTER' && (
        <FormLayout>
          <>
            <FormHeader heading="About" />
            <EditProfileLink />
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

export default MyProfile;
