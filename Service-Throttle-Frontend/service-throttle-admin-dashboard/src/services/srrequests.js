const fetcrequests = async (authAxios, type) => {
  const res = await authAxios.get(`/service-request/all`);
  const _requestsss = await res.data;
  return _requestsss;
};

export function getrequests(authAxios) {
  return fetcrequests(authAxios);
}
