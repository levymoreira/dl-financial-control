(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('AccountTypeController', AccountTypeController);

    AccountTypeController.$inject = ['AccountType', 'AccountTypeSearch'];

    function AccountTypeController(AccountType, AccountTypeSearch) {

        var vm = this;

        vm.accountTypes = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            AccountType.query(function(result) {
                vm.accountTypes = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            AccountTypeSearch.query({query: vm.searchQuery}, function(result) {
                vm.accountTypes = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
