const fetchUsers = async (authAxios, type) => {
  try {
    const res = await authAxios.get(`/users/${type}`);

    const _users = await res.data;
    console.log(_users);
    return _users;
  } catch (error) {
    console.log(error);
  }
};

export function getCurrentUser(authAxios, type) {
  return fetchUsers(authAxios, type);
}
