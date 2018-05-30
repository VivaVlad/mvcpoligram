'use strict';

angular.module('crudApp').controller('AlbumController',
    ['AlbumService', '$scope',  function( AlbumService, $scope) {

        var self = this;
        self.album = {id:null,title:""};
        self.albums=[];

        self.submit = submit;
        self.getAllAlbums = getAllAlbums;
        self.createAlbum = createAlbum;
        self.updateAlbum = updateAlbum;
        self.removeAlbum = removeAlbum;
        self.editAlbum = editAlbum;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.album.id === undefined || self.album.id === null) {
                console.log('Saving New Album', self.album);
                createAlbum(self.album);
            } else {
                updateAlbum(self.album, self.album.id);
                console.log('Album updated with id ', self.album.id);
            }
        }

        function createAlbum(album) {
            console.log('About to create album');
            AlbumService.createAlbum(album)
                .then(
                    function (response) {
                        console.log('Album created successfully');
                        self.successMessage = 'Album created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.album={};
                        $scope.AlbumForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Album');
                        self.errorMessage = 'Error while creating Album: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateAlbum(album, id){
            console.log('About to update album');
            AlbumService.updateAlbum(album, id)
                .then(
                    function (response){
                        console.log('Album updated successfully');
                        self.successMessage='Album updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.AlbumForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Album');
                        self.errorMessage='Error while updating Album '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeAlbum(id){
            console.log('About to remove Album with id '+id);
            AlbumService.removeAlbum(id)
                .then(
                    function(){
                        console.log('Album '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing album '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllAlbums(){
            return AlbumService.getAllAlbums();
        }

        function editAlbum(id) {
            self.successMessage='';
            self.errorMessage='';
            AlbumService.getAlbum(id).then(
                function (album) {
                    self.album = album;
                },
                function (errResponse) {
                    console.error('Error while removing album ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.album={};
            $scope.AlbumForm.$setPristine(); //reset Form
        }
    }


    ]);