const fetcservice = async (authAxios, type) => {
  const res = await authAxios.get(`/vehicle/service/all`);
  const _servicess = await res.data;
  return _servicess;
};

const _addservices = async (authAxios, service) => {
  await authAxios.post(`/vehicle/service/add`, service).catch((err) => {
    console.log(err);
  });
};

const _updateservices = async (authAxios, service) => {
  await authAxios
    .put(`/vehicle/service/${service.id}/update`, service)
    .catch((err) => {
      console.log(err);
    });
};

const _deleteservice = async (authAxios, service) => {
  await authAxios
    .delete(`/vehicle/service/${service.id}/delete`)
    .catch((err) => {
      console.log(err);
    });
};

export function getservice(authAxios) {
  return fetcservice(authAxios);
}

export function addservices(authAxios, service) {
  return _addservices(authAxios, service);
}

export function updateservices(authAxios, service) {
  return _updateservices(authAxios, service);
}

export function deleteservices(authAxios, service) {
  return _deleteservice(authAxios, service);
}
