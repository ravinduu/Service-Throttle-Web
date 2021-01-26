const fetcVehicles = async (authAxios, type) => {
  const res = await authAxios.get(`/vehicle/${type}`);

  const _vehicless = await res.data;
  console.log(_vehicless);
  return _vehicless;
};

export function getVehicles(authAxios, type) {
  return fetcVehicles(authAxios, type);
}
