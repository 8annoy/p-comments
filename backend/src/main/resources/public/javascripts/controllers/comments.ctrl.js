/**
 * Created by eitannoy on 7/21/16.
 */
'use strict';

(function () {

    function CommentsCtrl(commentsService) {
        var self = this;
        this.comments = [];

        commentsService.getComments().then(function (comments) {
            angular.copy(comments, self.comments);
        });

        this.addComment = function () {
            var comment = {email: self.email, content: self.content};
            commentsService.addComment(comment).then(function (comment) {
                self.comments.unshift(comment);
            });
        };
    }

    angular.module('mean-comments').controller('CommentsCtrl', CommentsCtrl);
})();