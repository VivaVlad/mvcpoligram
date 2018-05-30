var app = angular.module('crudApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080',
    USER_SERVICE_API : 'http://localhost:8080/user/',
    ALBUM_SERVICE_API : 'http://localhost:8080/album/',

});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('users', {
                url: '/users',
                templateUrl: 'static/views/users.html',
                controller:'UserController',
                controllerAs:'UserCtrl',
                resolve: {
                    users: function ($q, UserService) {
                        console.log('Load all users');
                        var deferred = $q.defer();
                        UserService.loadAllUsers().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            }
            )
            .state('albums', {
                url: '/albums',
                templateUrl: 'static/views/albums.html',
                controller:'AlbumController',
                controllerAs:'AlbumCtrl',
                resolve: {
                    albums: function ($q, AlbumService) {
                        console.log('Load all users');
                        var deferred = $q.defer();
                        AlbumService.loadAllAlbums().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            }
            )
            .state('ualbums', {
                    url: '/ualbums',
                    templateUrl: 'static/views/albumForUser.html',
                    controller:'AlbumController',
                    controllerAs:'AlbumCtrl',
                    resolve: {
                        albums: function ($q, AlbumService) {
                            console.log('Load all users');
                            var deferred = $q.defer();
                            AlbumService.loadAllAlbums().then(deferred.resolve, deferred.resolve);
                            return deferred.promise;
                        }
                    }
                }
            )
            .state('home', {
                url: "/",
                templateUrl: 'static/views/home.html',
                controller: "ImageController as ImageCtrl"
            })
            .state('registration', {
                url: "/registration",
                templateUrl: 'static/views/registration.html',
                controller: "UserController as UserCtrl"
            })
            .state('login', {
                url: "/login",
                templateUrl: 'static/views/login.html',
                controller: "UserController as UserCtrl"
            }
            );
        $urlRouterProvider.otherwise('/');
    }]);

