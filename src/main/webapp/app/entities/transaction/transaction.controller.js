(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('TransactionController', TransactionController);

    TransactionController.$inject = ['Transaction'];

    function TransactionController(Transaction) {

        var vm = this;

        vm.transactions = [];

        loadAll();

        function loadAll() {
            Transaction.query(function(result) {
                vm.transactions = result;
                vm.searchQuery = null;
            });
        }
    }
})();
