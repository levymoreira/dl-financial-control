(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('AccountNameController', AccountNameController);

    AccountNameController.$inject = ['AccountName'];

    function AccountNameController(AccountName) {

        var vm = this;

        vm.accountNames = [];

        loadAll();

        function loadAll() {
            AccountName.query(function(result) {
                vm.accountNames = result;
                vm.searchQuery = null;
            });
        }
    }
})();
