/**
 * Created by eitannoy on 7/20/16.
 */
'use strict';
(function () {
    angular.module('mean-comments', ['ui.router', 'ngMaterial', 'angularjs-gravatardirective'])
        .config(function($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise('/');

            $stateProvider

                // HOME STATES AND NESTED VIEWS ========================================
                .state('home', {
                    url: '/',
                    templateUrl: 'views/commentsSection.html'
                });
        });
})();