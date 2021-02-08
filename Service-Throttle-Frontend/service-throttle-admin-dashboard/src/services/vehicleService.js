const fetcVehicles = async (authAxios, type) => {
  const res = await authAxios.get(`/vehicle/${type}/all`);

  const _vehicless = await res.data;
  console.log(_vehicless);
  return _vehicless;
};

const fetcVehicleParts = async (authAxios, type) => {
  const res = await authAxios.get(`/vehicle/${type}/all`);

  const _vehicless = await res.data;
  console.log("_vehicless");

  console.log(_vehicless);
  return _vehicless;
};

export function getVehicles(authAxios, type) {
  return fetcVehicles(authAxios, type);
}

export function getVehicleParts(authAxios, type) {
  return fetcVehicleParts(authAxios, type);
}
