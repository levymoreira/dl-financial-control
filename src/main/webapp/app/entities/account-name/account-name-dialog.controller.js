(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('AccountNameDialogController', AccountNameDialogController);

    AccountNameDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'AccountName', 'User', 'AccountType'];

    function AccountNameDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, AccountName, User, AccountType) {
        var vm = this;

        vm.accountName = entity;
        vm.clear = clear;
        vm.save = save;
        vm.users = User.query();
        vm.accounttypes = AccountType.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.accountName.id !== null) {
                AccountName.update(vm.accountName, onSaveSuccess, onSaveError);
            } else {
                AccountName.save(vm.accountName, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dlFinancialControlApp:accountNameUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
