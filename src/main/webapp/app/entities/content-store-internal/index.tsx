import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ContentStoreInternal from './content-store-internal';
import ContentStoreInternalDetail from './content-store-internal-detail';
import ContentStoreInternalUpdate from './content-store-internal-update';
import ContentStoreInternalDeleteDialog from './content-store-internal-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ContentStoreInternalUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ContentStoreInternalUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ContentStoreInternalDetail} />
      <ErrorBoundaryRoute path={match.url} component={ContentStoreInternal} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ContentStoreInternalDeleteDialog} />
  </>
);

export default Routes;
