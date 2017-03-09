(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('AccountTypeDialogController', AccountTypeDialogController);

    AccountTypeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'AccountType'];

    function AccountTypeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, AccountType) {
        var vm = this;

        vm.accountType = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.accountType.id !== null) {
                AccountType.update(vm.accountType, onSaveSuccess, onSaveError);
            } else {
                AccountType.save(vm.accountType, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dlFinancialControlApp:accountTypeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
