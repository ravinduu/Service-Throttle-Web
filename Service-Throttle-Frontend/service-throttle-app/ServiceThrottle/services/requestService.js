const _addRequest = async (authAxios, request) => {
  try {
    const res = await authAxios.post(`/service-request/new`, request);
    const _request = res.data;
    return _request;
  } catch (error) {
    console.log(error);
  }
};

const _getRequest = async (authAxios, username) => {
  try {
    const res = await authAxios.get(`/service-request/by-customer/${username}`);
    const _requests = res.data;
    return _requests;
  } catch (error) {
    console.log(error);
  }
};

export function addRequest(authAxios, request) {
  return _addRequest(authAxios, request);
}

export function getRequest(authAxios, username) {
  return _getRequest(authAxios, username);
}
