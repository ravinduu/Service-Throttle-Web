# Service Throttle Web &middot;
Service Throttle web is a web app for On-Demand Vehicle Detailing and Repair Service. Where, the customer can tell the Service Throttle as much as they know about their vehicle and repair or maintenance needs, along with their contact information. Then, one of the mobile mechanics of the service provider will come and fix the customer’s car in their home or office, or the customer should drop it off at the service provider’s shop if they had to.

There are three main components in this project
- Customer app ( Android | [React Native](https://github.com/facebook/react-native) | [Expo](https://github.com/expo/expo) )
- Admin dashboard ( [React JS V17.0.1](https://github.com/facebook/react))
- Backend server ( [Spring boot V2.3.4](https://spring.io/projects/spring-boot) | [PostgresQL](https://github.com/postgres/postgres) )

## Docker
### Admin Dashboard and Backend Server
1. Get the docker images
```
docker pull ravdsn/service-throttle-backend:v1.0
docker pull ravdsn/service-throttle-frontend-admin:v1.0
```
2. Create and start the container
```
docker-compose up
```

## Documentation
You can find the documentations from [wiki](https://github.com/ravdsn/Service-Throttle-Web/wiki).

## License
Service Throttel Web is [Apache-2.0 Licensed](https://github.com/ravdsn/Service-Throttle-Web/blob/master/LICENSE).
