(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('TransactionController', TransactionController);

    TransactionController.$inject = ['Transaction'];

    function TransactionController(Transaction) {

        var vm = this;

        //Filters
        vm.year = new Date().getFullYear();
        vm.month = (new Date().getMonth()+1).toString();

        vm.years = [];
        var i;
        for(i= 1899; i<=vm.year+100; i++)
            vm.years.push(i);


        vm.transactions = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            Transaction.query(function(result) {
                vm.transactions = result;
            });
        }

        function search() {
            console.log(vm.description);
            console.log(vm.account);
            debugger;
            /*if (!vm.searchQuery) {
                return vm.loadAll();
            }
            Transaction.query({query: vm.searchQuery}, function(result) {
                vm.transactions = result;
                vm.currentSearch = vm.searchQuery;
            });*/
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
