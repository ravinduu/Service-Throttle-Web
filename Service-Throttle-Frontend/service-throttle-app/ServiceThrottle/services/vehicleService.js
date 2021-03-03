const fetchEngines = async (authAxios) => {
  try {
    const res = await authAxios.get(`/vehicle/engine/all`);
    const data = res.data;
    return data;
  } catch (error) {
    console.log(error);
  }
};

const fetchMakes = async (authAxios) => {
  try {
    const res = await authAxios.get(`/vehicle/make/all`);
    const data = res.data;
    return data;
  } catch (error) {
    console.log(error);
  }
};

const fetchModels = async (authAxios) => {
  try {
    const res = await authAxios.get(`/vehicle/model/all`);
    const data = res.data;
    return data;
  } catch (error) {
    console.log(error);
  }
};

const _getMyVehicles = async (authAxios, username) => {
  try {
    const res = await authAxios.get(
      `/vehicle/customer-vehicle/by-user/${username}`
    );
    const data = res.data;
    return data;
  } catch (error) {
    console.log(error);
  }
};

const _addCustomerVehicle = async (authAxios, cvehicle) => {
  try {
    const res = await authAxios.post(`/vehicle/customer-vehicle/add`, cvehicle);
    const data = res.data;
    return data;
  } catch (error) {
    console.log(error);
  }
};

const _deleteMyVehicles = async (authAxios, id) => {
  try {
    const res = await authAxios.delete(
      `/vehicle/customer-vehicle/my-vehicles/${id}/remove`
    );
    const data = res.data;
    return data;
  } catch (error) {
    console.log(error);
  }
};

export function getEngines(authAxios) {
  return fetchEngines(authAxios);
}

export function getMakes(authAxios) {
  return fetchMakes(authAxios);
}

export function getModels(authAxios) {
  return fetchModels(authAxios);
}

export function addCustomerVehicle(authAxios, cvehicle) {
  return _addCustomerVehicle(authAxios, cvehicle);
}

export function getMyVehicles(authAxios, username) {
  return _getMyVehicles(authAxios, username);
}

export function deleteMyVehicles(authAxios, username) {
  return _deleteMyVehicles(authAxios, username);
}
