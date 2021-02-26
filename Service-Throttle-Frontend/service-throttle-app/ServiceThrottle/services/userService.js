const fetchUsers = async (authAxios, username) => {
  try {
    const res = await authAxios.get(`/account/${username}`);
    const user = res.data;
    return user;
  } catch (error) {
    console.log(error);
  }
};

export function getCurrentUser(authAxios, username) {
  return fetchUsers(authAxios, username);
}
