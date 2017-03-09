(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('CreditCardInvoiceController', CreditCardInvoiceController);

    CreditCardInvoiceController.$inject = ['CreditCardInvoice', 'CreditCardInvoiceSearch'];

    function CreditCardInvoiceController(CreditCardInvoice, CreditCardInvoiceSearch) {

        var vm = this;

        vm.creditCardInvoices = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            CreditCardInvoice.query(function(result) {
                vm.creditCardInvoices = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            CreditCardInvoiceSearch.query({query: vm.searchQuery}, function(result) {
                vm.creditCardInvoices = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
