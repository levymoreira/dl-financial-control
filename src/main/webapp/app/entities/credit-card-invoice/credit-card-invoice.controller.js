(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('CreditCardInvoiceController', CreditCardInvoiceController);

    CreditCardInvoiceController.$inject = ['CreditCardInvoice'];

    function CreditCardInvoiceController(CreditCardInvoice) {

        var vm = this;

        vm.creditCardInvoices = [];

        loadAll();

        function loadAll() {
            CreditCardInvoice.query(function(result) {
                vm.creditCardInvoices = result;
                vm.searchQuery = null;
            });
        }
    }
})();
