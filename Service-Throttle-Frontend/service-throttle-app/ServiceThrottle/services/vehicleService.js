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

export function getEngines(authAxios) {
  return fetchEngines(authAxios);
}

export function getMakes(authAxios) {
  return fetchMakes(authAxios);
}

export function getModels(authAxios) {
  return fetchModels(authAxios);
}
