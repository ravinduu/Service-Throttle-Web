const fetcpromotion = async (authAxios, type) => {
  const res = await authAxios.get(`/promotion/all`);
  const _promotionss = await res.data;
  return _promotionss;
};

const _addPromotions = async (authAxios, promo) => {
  console.log("JSSS");
  console.log(promo);
  const res = await authAxios.post(`/promotion/add`, promo).catch(() => {
    console.log("Errrr");
  });
  const _promotionss = await res.data;
  return _promotionss;
};

export function getPromotion(authAxios) {
  return fetcpromotion(authAxios);
}

export function addPromotions(authAxios, promo) {
  return _addPromotions(authAxios, promo);
}
