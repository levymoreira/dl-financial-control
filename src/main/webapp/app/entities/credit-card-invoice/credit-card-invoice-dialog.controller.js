(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .controller('CreditCardInvoiceDialogController', CreditCardInvoiceDialogController);

    CreditCardInvoiceDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CreditCardInvoice', 'AccountName'];

    function CreditCardInvoiceDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CreditCardInvoice, AccountName) {
        var vm = this;

        vm.creditCardInvoice = entity;
        vm.clear = clear;
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
            if (vm.creditCardInvoice.id !== null) {
                CreditCardInvoice.update(vm.creditCardInvoice, onSaveSuccess, onSaveError);
            } else {
                CreditCardInvoice.save(vm.creditCardInvoice, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('dlFinancialControlApp:creditCardInvoiceUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
