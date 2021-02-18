const fetcpromotion = async (authAxios, type) => {
  const res = await authAxios.get(`/promotion/all`);
  const _promotionss = await res.data;
  return _promotionss;
};

export function getPromotion(authAxios) {
  return fetcpromotion(authAxios);
}
