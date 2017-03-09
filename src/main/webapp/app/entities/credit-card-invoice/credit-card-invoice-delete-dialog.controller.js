(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('CreditCardInvoiceDeleteController',CreditCardInvoiceDeleteController);

    CreditCardInvoiceDeleteController.$inject = ['$uibModalInstance', 'entity', 'CreditCardInvoice'];

    function CreditCardInvoiceDeleteController($uibModalInstance, entity, CreditCardInvoice) {
        var vm = this;

        vm.creditCardInvoice = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            CreditCardInvoice.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
