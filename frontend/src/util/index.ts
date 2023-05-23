export const retreiveTokens = () => {
  const storage = localStorage.getItem('tokens');
  let tokens;
  if (storage) {
    tokens = JSON.parse(storage);
  }
  return tokens;
};

export const slugify = (firstName: string, lastName: string) => {
  return (firstName + lastName).toLowerCase();
};
