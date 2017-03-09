(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('InstallmentGroupDialogController', InstallmentGroupDialogController);

    InstallmentGroupDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'InstallmentGroup', 'AccountName'];

    function InstallmentGroupDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, InstallmentGroup, AccountName) {
        var vm = this;

        vm.installmentGroup = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.accountnames = AccountName.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.installmentGroup.id !== null) {
                InstallmentGroup.update(vm.installmentGroup, onSaveSuccess, onSaveError);
            } else {
                InstallmentGroup.save(vm.installmentGroup, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dlFinancialControlApp:installmentGroupUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.date = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
