const fetchUsers = async (authAxios, type) => {
  const res = await authAxios.get(`/users/${type}`);

  const _users = await res.data;
  console.log(_users);
  return _users;
};

export function getUsers(authAxios, type) {
  return fetchUsers(authAxios, type);
}
