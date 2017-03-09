(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('AccountNameDetailController', AccountNameDetailController);

    AccountNameDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'AccountName', 'User', 'AccountType'];

    function AccountNameDetailController($scope, $rootScope, $stateParams, previousState, entity, AccountName, User, AccountType) {
        var vm = this;

        vm.accountName = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dlFinancialControlApp:accountNameUpdate', function(event, result) {
            vm.accountName = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
