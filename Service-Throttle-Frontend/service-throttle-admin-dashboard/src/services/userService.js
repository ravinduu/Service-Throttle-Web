const fetchUsers = async (authAxios, type) => {
  const res = await authAxios.get(`/users/${type}`);

  const _customers = await res.data;
  console.log(_customers);
  return _customers;
};

export function getUsers(authAxios, type) {
  return fetchUsers(authAxios, type);
}
