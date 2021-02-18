const fetcpromotion = async (authAxios, type) => {
  const res = await authAxios.get(`/promotion/all`);
  const _promotionss = await res.data;
  return _promotionss;
};

const _addPromotions = async (authAxios, promo) => {
  await authAxios.post(`/promotion/add`, promo).catch((err) => {
    console.log(err);
  });
};

const _updatePromotions = async (authAxios, promo) => {
  await authAxios.put(`/promotion/${promo.id}/update`, promo).catch((err) => {
    console.log(err);
  });
};

const _deletePromotion = async (authAxios, promo) => {
  await authAxios.delete(`/promotion/${promo.id}/delete`).catch((err) => {
    console.log(err);
  });
};

export function getPromotion(authAxios) {
  return fetcpromotion(authAxios);
}

export function addPromotions(authAxios, promo) {
  return _addPromotions(authAxios, promo);
}

export function updatePromotions(authAxios, promo) {
  return _updatePromotions(authAxios, promo);
}

export function deletePromotions(authAxios, promo) {
  return _deletePromotion(authAxios, promo);
}
