(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('AccountTypeDetailController', AccountTypeDetailController);

    AccountTypeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'AccountType'];

    function AccountTypeDetailController($scope, $rootScope, $stateParams, previousState, entity, AccountType) {
        var vm = this;

        vm.accountType = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dlFinancialControlApp:accountTypeUpdate', function(event, result) {
            vm.accountType = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
