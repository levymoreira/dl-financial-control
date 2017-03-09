(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('credit-card-invoice', {
            parent: 'entity',
            url: '/credit-card-invoice',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dlFinancialControlApp.creditCardInvoice.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/credit-card-invoice/credit-card-invoices.html',
                    controller: 'CreditCardInvoiceController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('creditCardInvoice');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('credit-card-invoice-detail', {
            parent: 'credit-card-invoice',
            url: '/credit-card-invoice/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dlFinancialControlApp.creditCardInvoice.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/credit-card-invoice/credit-card-invoice-detail.html',
                    controller: 'CreditCardInvoiceDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('creditCardInvoice');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'CreditCardInvoice', function($stateParams, CreditCardInvoice) {
                    return CreditCardInvoice.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'credit-card-invoice',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('credit-card-invoice-detail.edit', {
            parent: 'credit-card-invoice-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/credit-card-invoice/credit-card-invoice-dialog.html',
                    controller: 'CreditCardInvoiceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CreditCardInvoice', function(CreditCardInvoice) {
                            return CreditCardInvoice.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('credit-card-invoice.new', {
            parent: 'credit-card-invoice',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/credit-card-invoice/credit-card-invoice-dialog.html',
                    controller: 'CreditCardInvoiceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                year: null,
                                month: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('credit-card-invoice', null, { reload: 'credit-card-invoice' });
                }, function() {
                    $state.go('credit-card-invoice');
                });
            }]
        })
        .state('credit-card-invoice.edit', {
            parent: 'credit-card-invoice',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/credit-card-invoice/credit-card-invoice-dialog.html',
                    controller: 'CreditCardInvoiceDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CreditCardInvoice', function(CreditCardInvoice) {
                            return CreditCardInvoice.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('credit-card-invoice', null, { reload: 'credit-card-invoice' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('credit-card-invoice.delete', {
            parent: 'credit-card-invoice',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/credit-card-invoice/credit-card-invoice-delete-dialog.html',
                    controller: 'CreditCardInvoiceDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['CreditCardInvoice', function(CreditCardInvoice) {
                            return CreditCardInvoice.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('credit-card-invoice', null, { reload: 'credit-card-invoice' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
