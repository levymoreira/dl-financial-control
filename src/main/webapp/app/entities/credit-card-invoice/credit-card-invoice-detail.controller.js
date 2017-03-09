(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('CreditCardInvoiceDetailController', CreditCardInvoiceDetailController);

    CreditCardInvoiceDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'CreditCardInvoice', 'AccountName'];

    function CreditCardInvoiceDetailController($scope, $rootScope, $stateParams, previousState, entity, CreditCardInvoice, AccountName) {
        var vm = this;

        vm.creditCardInvoice = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('dlFinancialControlApp:creditCardInvoiceUpdate', function(event, result) {
            vm.creditCardInvoice = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
