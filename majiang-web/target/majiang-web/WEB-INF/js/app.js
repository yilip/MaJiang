/**
 * Created by Administrator on 2016/8/16 0016.
 */
// Define the `phonecatApp` module
var phonecatApp = angular.module('phonecatApp', []);

// Define the `PhoneListController` controller on the `phonecatApp` module
phonecatApp.controller('InitController', ["$scope",function PhoneListController($scope) {
    $scope.handInMaItems = ["一万","一万","一万","一万","一万","一万","一万","一万","一万"
    ];
}]);
phonecatApp.controller('helloController', function PhoneListController($scope) {
    $scope.hello="hello lip";
});
