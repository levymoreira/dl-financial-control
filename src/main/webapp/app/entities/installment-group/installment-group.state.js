(function() {
    'use strict';

    angular
        .module('dlFinancialControlApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('installment-group', {
            parent: 'entity',
            url: '/installment-group',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dlFinancialControlApp.installmentGroup.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/installment-group/installment-groups.html',
                    controller: 'InstallmentGroupController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('installmentGroup');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('installment-group-detail', {
            parent: 'installment-group',
            url: '/installment-group/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'dlFinancialControlApp.installmentGroup.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/installment-group/installment-group-detail.html',
                    controller: 'InstallmentGroupDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('installmentGroup');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'InstallmentGroup', function($stateParams, InstallmentGroup) {
                    return InstallmentGroup.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'installment-group',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('installment-group-detail.edit', {
            parent: 'installment-group-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/installment-group/installment-group-dialog.html',
                    controller: 'InstallmentGroupDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['InstallmentGroup', function(InstallmentGroup) {
                            return InstallmentGroup.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('installment-group.new', {
            parent: 'installment-group',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/installment-group/installment-group-dialog.html',
                    controller: 'InstallmentGroupDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                date: null,
                                description: null,
                                installments: null,
                                amount: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('installment-group', null, { reload: 'installment-group' });
                }, function() {
                    $state.go('installment-group');
                });
            }]
        })
        .state('installment-group.edit', {
            parent: 'installment-group',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/installment-group/installment-group-dialog.html',
                    controller: 'InstallmentGroupDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['InstallmentGroup', function(InstallmentGroup) {
                            return InstallmentGroup.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('installment-group', null, { reload: 'installment-group' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('installment-group.delete', {
            parent: 'installment-group',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/installment-group/installment-group-delete-dialog.html',
                    controller: 'InstallmentGroupDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['InstallmentGroup', function(InstallmentGroup) {
                            return InstallmentGroup.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('installment-group', null, { reload: 'installment-group' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
