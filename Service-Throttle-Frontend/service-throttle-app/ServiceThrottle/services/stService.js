const fetchStServices = async (authAxios) => {
  try {
    const res = await authAxios.get(`/vehicle/service/all`);
    const services = res.data;
    return services;
  } catch (error) {
    console.log(error);
  }
};

const fetchPromotions = async (authAxios) => {
  try {
    const res = await authAxios.get(`/promotion/all`);
    const promotions = res.data;
    return promotions;
  } catch (error) {
    console.log(error);
  }
};

export function getServices(authAxios) {
  return fetchStServices(authAxios);
}

export function getPromotions(authAxios) {
  return fetchPromotions(authAxios);
}
