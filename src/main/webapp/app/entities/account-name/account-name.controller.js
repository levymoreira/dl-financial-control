(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('AccountNameController', AccountNameController);

    AccountNameController.$inject = ['AccountName', 'AccountNameSearch'];

    function AccountNameController(AccountName, AccountNameSearch) {

        var vm = this;

        vm.accountNames = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            AccountName.query(function(result) {
                vm.accountNames = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            AccountNameSearch.query({query: vm.searchQuery}, function(result) {
                vm.accountNames = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
