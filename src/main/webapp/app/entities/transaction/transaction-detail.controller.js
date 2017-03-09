(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('TransactionDetailController', TransactionDetailController);

    TransactionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Transaction', 'User', 'InstallmentGroup', 'CreditCardInvoice', 'Category', 'Client', 'AccountName'];

    function TransactionDetailController($scope, $rootScope, $stateParams, previousState, entity, Transaction, User, InstallmentGroup, CreditCardInvoice, Category, Client, AccountName) {
        var vm = this;

        vm.transaction = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dlFinancialControlApp:transactionUpdate', function(event, result) {
            vm.transaction = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
