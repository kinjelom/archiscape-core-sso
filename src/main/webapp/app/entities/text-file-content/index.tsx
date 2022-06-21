import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TextFileContent from './text-file-content';
import TextFileContentDetail from './text-file-content-detail';
import TextFileContentUpdate from './text-file-content-update';
import TextFileContentDeleteDialog from './text-file-content-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TextFileContentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TextFileContentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TextFileContentDetail} />
      <ErrorBoundaryRoute path={match.url} component={TextFileContent} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TextFileContentDeleteDialog} />
  </>
);

export default Routes;
