const fetcVehicles = async (authAxios, type) => {
  const res = await authAxios.get(`/vehicle/${type}/all`);

  const _vehicless = await res.data;

  return _vehicless;
};

const fetcVehicleParts = async (authAxios, type) => {
  const res = await authAxios.get(`/vehicle/${type}/all`);

  const _vehicless = await res.data;

  return _vehicless;
};

const _updateVehicleParts = async (authAxios, data, type) => {
  const res = await authAxios.put(`/vehicle/${type}/update/${data.id}`, data);

  const _vehicless = await res.data;

  return _vehicless;
};

const _deleteVehicleParts = async (authAxios, data, type) => {
  const res = await authAxios.delete(`/vehicle/${type}/delete/${data.id}`);

  const _vehicless = await res.data;

  return _vehicless;
};

const _addVehicleParts = async (authAxios, data, type) => {
  const res = await authAxios.post(`/vehicle/${type}/add`, data);

  const _vehicless = await res.data;

  return _vehicless;
};

export function getVehicles(authAxios, type) {
  return fetcVehicles(authAxios, type);
}

export function getVehicleParts(authAxios, type) {
  return fetcVehicleParts(authAxios, type);
}

export function updateVehicleParts(authAxios, data, type) {
  return _updateVehicleParts(authAxios, data, type);
}

export function deleteVehicleParts(authAxios, data, type) {
  return _deleteVehicleParts(authAxios, data, type);
}

export function addVehicleParts(authAxios, data, type) {
  return _addVehicleParts(authAxios, data, type);
}
