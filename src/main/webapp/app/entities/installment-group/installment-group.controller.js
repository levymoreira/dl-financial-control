(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('InstallmentGroupController', InstallmentGroupController);

    InstallmentGroupController.$inject = ['InstallmentGroup'];

    function InstallmentGroupController(InstallmentGroup) {

        var vm = this;

        vm.installmentGroups = [];

        loadAll();

        function loadAll() {
            InstallmentGroup.query(function(result) {
                vm.installmentGroups = result;
                vm.searchQuery = null;
            });
        }
    }
})();
