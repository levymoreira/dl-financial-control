(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('AccountTypeDeleteController',AccountTypeDeleteController);

    AccountTypeDeleteController.$inject = ['$uibModalInstance', 'entity', 'AccountType'];

    function AccountTypeDeleteController($uibModalInstance, entity, AccountType) {
        var vm = this;

        vm.accountType = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            AccountType.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
