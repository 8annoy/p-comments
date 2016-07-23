/**
 * Created by eitannoy on 7/21/16.
 */
'use strict';

(function () {

    function CommentsService($http) {
        this.getComments = function () {
            return $http.get('/api/v1/comments').then(function (response) {
                return response.data;
            });
        };

        this.addComment = function (comment) {
            return $http.post('/api/v1/comments', comment).then(function (response) {
                return response.data;
            });
        };
    }
    angular.module('mean-comments').factory('commentsService', ['$http', function($http) {return new CommentsService($http)}]);
})();
