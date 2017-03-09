(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('InstallmentGroupDetailController', InstallmentGroupDetailController);

    InstallmentGroupDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'InstallmentGroup', 'AccountName'];

    function InstallmentGroupDetailController($scope, $rootScope, $stateParams, previousState, entity, InstallmentGroup, AccountName) {
        var vm = this;

        vm.installmentGroup = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dlFinancialControlApp:installmentGroupUpdate', function(event, result) {
            vm.installmentGroup = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
