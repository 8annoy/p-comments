/**
 * Created by eitannoy on 7/21/16.
 */
'use strict';

(function () {

    function CommentsCtrl(commentsService, $mdDialog) {
        var self = this;
        var ERROR_MESSAGE = 'Inconsistencies in time-space are messing with our ability to fulfill your request. Please try again later.'
        this.error = '';
        this.comments = [];

        commentsService.getComments().then(function (comments) {
            angular.copy(comments, self.comments);
        },
        function(data) {
            self.showAlert();
        });

        this.addComment = function () {
            var comment = {email: self.email, content: self.content};
            commentsService.addComment(comment).then(function (comment) {
                self.error = '';
                self.comments.unshift(comment);
            },
            function(data) {
                self.showAlert();
            });
        };

        this.showAlert = function(ev) {
            $mdDialog.show(
                $mdDialog.alert()
                    .parent(angular.element(document.querySelector('#popupContainer')))
                    .clickOutsideToClose(false)
                    .title('This is embarrassing')
                    .textContent(ERROR_MESSAGE)
                    .ariaLabel('Alert')
                    .ok("It's OK :)")
                    .targetEvent(ev)
            );
        }
    }

    angular.module('mean-comments').controller('CommentsCtrl', CommentsCtrl);
})();