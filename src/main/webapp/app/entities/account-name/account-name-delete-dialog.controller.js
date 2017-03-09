(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('AccountNameDeleteController',AccountNameDeleteController);

    AccountNameDeleteController.$inject = ['$uibModalInstance', 'entity', 'AccountName'];

    function AccountNameDeleteController($uibModalInstance, entity, AccountName) {
        var vm = this;

        vm.accountName = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            AccountName.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
