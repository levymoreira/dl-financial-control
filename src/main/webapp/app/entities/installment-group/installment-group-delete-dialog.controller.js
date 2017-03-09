(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('InstallmentGroupDeleteController',InstallmentGroupDeleteController);

    InstallmentGroupDeleteController.$inject = ['$uibModalInstance', 'entity', 'InstallmentGroup'];

    function InstallmentGroupDeleteController($uibModalInstance, entity, InstallmentGroup) {
        var vm = this;

        vm.installmentGroup = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            InstallmentGroup.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
