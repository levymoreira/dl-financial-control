(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('AccountTypeController', AccountTypeController);

    AccountTypeController.$inject = ['AccountType'];

    function AccountTypeController(AccountType) {

        var vm = this;

        vm.accountTypes = [];

        loadAll();

        function loadAll() {
            AccountType.query(function(result) {
                vm.accountTypes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
