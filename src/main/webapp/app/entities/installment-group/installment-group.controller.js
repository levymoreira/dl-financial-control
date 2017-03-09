(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('InstallmentGroupController', InstallmentGroupController);

    InstallmentGroupController.$inject = ['InstallmentGroup', 'InstallmentGroupSearch'];

    function InstallmentGroupController(InstallmentGroup, InstallmentGroupSearch) {

        var vm = this;

        vm.installmentGroups = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            InstallmentGroup.query(function(result) {
                vm.installmentGroups = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            InstallmentGroupSearch.query({query: vm.searchQuery}, function(result) {
                vm.installmentGroups = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
