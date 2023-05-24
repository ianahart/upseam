import { useContext } from 'react';
import InitialIcon from './InitialIcon';
import ProfilePicture from './ProfilePicture';
import { UserContext } from '../../context/user';
import { IUserContext } from '../../interfaces';

const Avatar = () => {
  const { user } = useContext(UserContext) as IUserContext;

  return <>{user.avatarUrl ? <ProfilePicture /> : <InitialIcon />}</>;
};

export default Avatar;
